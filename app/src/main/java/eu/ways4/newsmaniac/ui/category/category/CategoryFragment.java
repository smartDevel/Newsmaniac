package eu.ways4.newsmaniac.ui.category.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import eu.ways4.newsmaniac.R;
import eu.ways4.newsmaniac.databinding.FragmentCategoryBinding;

import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.BUSINESS_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.ENTERTAINMENT_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.GENERAL_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.HEALTH_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.SCIENCE_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.SPORTS_PAGE_INDEX;
import static eu.ways4.newsmaniac.ui.category.category.CategoryPagerAdapter.TECHNOLOGY_PAGE_INDEX;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;
    ViewPager2 viewPager2;
    CategoryPagerAdapter pagerAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager2 = binding.viewpager;
        pagerAdapter = new CategoryPagerAdapter(this);
        TabLayout tabLayout = binding.tabs;
        viewPager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(getTabTitle(position))).attach();
    }

    private String getTabTitle(int position) {
        String title = "";
        switch (position) {
            case ENTERTAINMENT_PAGE_INDEX:
                title = getString(R.string.entertainement_txt);
                break;
            case HEALTH_PAGE_INDEX:
                title = getString(R.string.health_txt);
                break;
            case SPORTS_PAGE_INDEX:
                title = getString(R.string.sports_txt);
                break;
            case TECHNOLOGY_PAGE_INDEX:
                title = getString(R.string.tech_txt);
                break;
            case GENERAL_PAGE_INDEX:
                title = getString(R.string.general_txt);
                break;
            case BUSINESS_PAGE_INDEX:
                title = getString(R.string.business_txt);
                break;
            case SCIENCE_PAGE_INDEX:
                title = getString(R.string.science_txt);
                break;
        }
        return title;
    }
}
